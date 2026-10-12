import { useMutation, useQuery, useQueryClient } from '@tanstack/react-query';
import { studyPlanApi } from '../api/studyPlanApi';

export const useStudyPlans = () => {
  return useQuery({
    queryKey: ['study-plans'],
    queryFn: async () => {
      const response = await studyPlanApi.getPlans();
      return response.data.data;
    },
  });
};

export const useStudyPlan = (id: number) => {
  return useQuery({
    queryKey: ['study-plans', id],
    queryFn: async () => {
      const response = await studyPlanApi.getPlan(id);
      return response.data.data;
    },
    enabled: !!id,
  });
};

export const useGenerateStudyPlan = () => {
  const queryClient = useQueryClient();
  return useMutation({
    mutationFn: studyPlanApi.generatePlan,
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['study-plans'] });
    },
  });
};

export const useUpdateSession = () => {
  const queryClient = useQueryClient();
  return useMutation({
    mutationFn: ({ sessionId, data }: { sessionId: number; data: any }) =>
      studyPlanApi.updateSession(sessionId, data),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['study-plans'] });
    },
  });
};

export const useCompleteSession = () => {
  const queryClient = useQueryClient();
  return useMutation({
    mutationFn: studyPlanApi.completeSession,
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['study-plans'] });
    },
  });
};
