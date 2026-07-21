import { api } from "./client";

export type AuthResponse = {
    token: string;
    username: string;
};

export type RegisterRequest = {
    username: string;
    email: string;
    password: string;
};

export type LoginRequest = {
    username: string;
    password: string;
};

export async function register(request: RegisterRequest): Promise<AuthResponse> {
    const response = await api.post<AuthResponse>("/auth/register", request);
    return response.data;
}

export async function login(request: LoginRequest): Promise<AuthResponse> {
    const response = await api.post<AuthResponse>("/auth/login", request);
    return response.data;
}