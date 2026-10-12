import { ApiResponse } from '../types/auth';
import axiosClient from './axiosClient';

export interface AiChatRequest {
  message: string;
  context?: string;
}

export interface AiChatResponse {
  message: string;
  context: string;
  responseTime: number;
}

export const aiApi = {
  chat: (data: AiChatRequest) =>
    axiosClient.post<ApiResponse<AiChatResponse>>('/ai/chat', data),

  generateQuiz: (topic: string) =>
    axiosClient.post<ApiResponse<string>>('/ai/generate-quiz', null, {
      params: { topic },
    }),

  explainTopic: (topic: string) =>
    axiosClient.post<ApiResponse<string>>('/ai/explain-topic', null, {
      params: { topic },
    }),

  recommendProjects: (domain: string) =>
    axiosClient.post<ApiResponse<string>>('/ai/recommend-projects', null, {
      params: { domain },
    }),
};
