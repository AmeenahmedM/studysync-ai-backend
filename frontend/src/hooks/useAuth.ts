import { useQuery, useMutation, useQueryClient } from '@tanstack/react-query';
import { authApi } from '../api/authApi';
import { AxiosError } from 'axios';

export const useCurrentUser = () => {
  return useQuery({
    queryKey: ['user', 'current'],
    queryFn: async () => {
      const response = await authApi.getCurrentUser();
      return response.data.data;
    },
    retry: 1,
  });
};

export const useLogin = () => {
  const queryClient = useQueryClient();
  return useMutation({
    mutationFn: authApi.login,
    onSuccess: (response) => {
      const { token, ...user } = response.data.data;
      queryClient.setQueryData(['user', 'current'], user);
    },
  });
};

export const useRegister = () => {
  return useMutation({
    mutationFn: authApi.register,
  });
};

export const useUpdateProfile = () => {
  const queryClient = useQueryClient();
  return useMutation({
    mutationFn: authApi.updateProfile,
    onSuccess: (response) => {
      queryClient.setQueryData(['user', 'current'], response.data.data);
    },
  });
};
