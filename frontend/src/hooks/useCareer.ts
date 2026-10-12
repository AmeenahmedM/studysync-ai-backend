import { useMutation, useQuery, useQueryClient } from '@tanstack/react-query';
import { careerApi } from '../api/careerApi';

export const useCareerDomains = () => {
  return useQuery({
    queryKey: ['career-domains'],
    queryFn: async () => {
      const response = await careerApi.getDomains();
      return response.data.data;
    },
  });
};

export const useGenerateCareerRoadmap = () => {
  const queryClient = useQueryClient();
  return useMutation({
    mutationFn: careerApi.generateRoadmap,
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['career-roadmaps'] });
    },
  });
};

export const useCareerRoadmap = (id: number) => {
  return useQuery({
    queryKey: ['career-roadmaps', id],
    queryFn: async () => {
      const response = await careerApi.getRoadmap(id);
      return response.data.data;
    },
    enabled: !!id,
  });
};

export const useSkillGapAnalysis = () => {
  return useMutation({
    mutationFn: careerApi.analyzeSkillGap,
  });
};
