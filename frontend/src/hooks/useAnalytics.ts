import { useQuery, useMutation } from '@tanstack/react-query';
import { analyticsApi } from '../api/analyticsApi';

export const useAnalyticsDashboard = () => {
  return useQuery({
    queryKey: ['analytics', 'dashboard'],
    queryFn: async () => {
      const response = await analyticsApi.getDashboard();
      return response.data.data;
    },
  });
};

export const useWeeklyProgress = () => {
  return useQuery({
    queryKey: ['analytics', 'weekly'],
    queryFn: async () => {
      const response = await analyticsApi.getWeeklyProgress();
      return response.data.data;
    },
  });
};

export const useNotifications = () => {
  return useQuery({
    queryKey: ['notifications'],
    queryFn: async () => {
      const response = await analyticsApi.getNotifications();
      return response.data.data;
    },
    refetchInterval: 30000,
  });
};

export const useMarkNotificationAsRead = () => {
  return useMutation({
    mutationFn: analyticsApi.markNotificationAsRead,
  });
};

export const useSimulate = () => {
  return useMutation({
    mutationFn: analyticsApi.simulate,
  });
};
