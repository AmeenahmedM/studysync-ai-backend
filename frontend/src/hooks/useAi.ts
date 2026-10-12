import { useMutation } from '@tanstack/react-query';
import { aiApi } from '../api/aiApi';

export const useAiChat = () => {
  return useMutation({
    mutationFn: aiApi.chat,
  });
};

export const useGenerateQuiz = () => {
  return useMutation({
    mutationFn: (topic: string) => aiApi.generateQuiz(topic),
  });
};

export const useExplainTopic = () => {
  return useMutation({
    mutationFn: (topic: string) => aiApi.explainTopic(topic),
  });
};

export const useRecommendProjects = () => {
  return useMutation({
    mutationFn: (domain: string) => aiApi.recommendProjects(domain),
  });
};
