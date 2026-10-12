import { ApiResponse } from '../types/auth';
import { AnalyticsDashboard, Notification, ProgressRecord, SimulationRequest, SimulationResponse } from '../types/analytics';
import axiosClient from './axiosClient';

export const analyticsApi = {
  getDashboard: () =>
    axiosClient.get<ApiResponse<AnalyticsDashboard>>('/analytics/dashboard'),

  getWeeklyProgress: () =>
    axiosClient.get<ApiResponse<ProgressRecord[]>>('/analytics/weekly'),

  getStudyProgress: () =>
    axiosClient.get<ApiResponse<unknown>>('/analytics/study-progress'),

  getCareerProgress: () =>
    axiosClient.get<ApiResponse<unknown>>('/analytics/career-progress'),

  simulate: (data: SimulationRequest) =>
    axiosClient.post<ApiResponse<SimulationResponse>>('/planning/simulate', data),

  getNotifications: () =>
    axiosClient.get<ApiResponse<Notification[]>>('/notifications'),

  markNotificationAsRead: (id: number) =>
    axiosClient.put<ApiResponse<Notification>>(`/notifications/${id}/read`),
};
