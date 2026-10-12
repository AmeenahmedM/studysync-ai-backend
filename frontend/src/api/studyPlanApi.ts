import { ApiResponse } from '../types/auth';
import { StudyPlan, StudyPlanRequest, StudySession, UpdateSessionRequest } from '../types/study';
import axiosClient from './axiosClient';

export const studyPlanApi = {
  generatePlan: (data: StudyPlanRequest) =>
    axiosClient.post<ApiResponse<StudyPlan>>('/study-plans/generate', data),

  getPlans: () =>
    axiosClient.get<ApiResponse<StudyPlan[]>>('/study-plans'),

  getPlan: (id: number) =>
    axiosClient.get<ApiResponse<StudyPlan>>(`/study-plans/${id}`),

  regeneratePlan: (id: number) =>
    axiosClient.post<ApiResponse<StudyPlan>>(`/study-plans/${id}/regenerate`),

  updateSession: (sessionId: number, data: UpdateSessionRequest) =>
    axiosClient.put<ApiResponse<StudySession>>(`/study-sessions/${sessionId}`, data),

  completeSession: (sessionId: number) =>
    axiosClient.post<ApiResponse<StudySession>>(`/study-sessions/${sessionId}/complete`),
};
