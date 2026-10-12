import { ApiResponse } from '../types/auth';
import { CreateSubjectRequest, CreateTopicRequest, Subject } from '../types/subject';
import axiosClient from './axiosClient';

export const subjectApi = {
  getSubjects: () =>
    axiosClient.get<ApiResponse<Subject[]>>('/subjects'),

  createSubject: (data: CreateSubjectRequest) =>
    axiosClient.post<ApiResponse<Subject>>('/subjects', data),

  updateSubject: (id: number, data: Partial<Subject>) =>
    axiosClient.put<ApiResponse<Subject>>(`/subjects/${id}`, data),

  deleteSubject: (id: number) =>
    axiosClient.delete<ApiResponse<void>>(`/subjects/${id}`),

  addTopic: (subjectId: number, data: CreateTopicRequest) =>
    axiosClient.post(`/subjects/${subjectId}/topics`, data),
};
