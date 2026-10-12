import { ApiResponse, AuthResponse, LoginRequest, RegisterRequest, User } from '../types/auth';
import axiosClient from './axiosClient';

export const authApi = {
  register: (data: RegisterRequest) =>
    axiosClient.post<ApiResponse<AuthResponse>>('/auth/register', data),

  login: (data: LoginRequest) =>
    axiosClient.post<ApiResponse<AuthResponse>>('/auth/login', data),

  getCurrentUser: () =>
    axiosClient.get<ApiResponse<User>>('/users/me'),

  updateProfile: (data: Partial<User>) =>
    axiosClient.put<ApiResponse<User>>('/users/me', data),

  logout: () => {
    localStorage.removeItem('auth_token');
    localStorage.removeItem('user');
  },
};
