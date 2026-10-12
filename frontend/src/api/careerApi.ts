import { ApiResponse } from '../types/auth';
import {
  CareerDomain,
  CareerRoadmap,
  CareerRoadmapRequest,
  SkillGapAnalysis,
  SkillGapRequest,
} from '../types/career';
import axiosClient from './axiosClient';

export const careerApi = {
  getDomains: () =>
    axiosClient.get<ApiResponse<CareerDomain[]>>('/career-domains'),

  generateRoadmap: (data: CareerRoadmapRequest) =>
    axiosClient.post<ApiResponse<CareerRoadmap>>('/career-roadmaps/generate', data),

  getRoadmap: (id: number) =>
    axiosClient.get<ApiResponse<CareerRoadmap>>(`/career-roadmaps/${id}`),

  analyzeSkillGap: (data: SkillGapRequest) =>
    axiosClient.post<ApiResponse<SkillGapAnalysis>>('/skills/analyze', data),
};
