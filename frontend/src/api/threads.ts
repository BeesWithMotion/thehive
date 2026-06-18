import {apiGet, apiPost} from "./client";
import type { CreateThreadRequest, Thread } from "../types/thread";

export function createThread(
    boardAbbreviation: string,
    request: CreateThreadRequest
) {
    return apiPost<Thread, CreateThreadRequest>(
        `/api/boards/${boardAbbreviation}/threads`,
        request,
    );
}

export function getThread(boardAbbreviation: string, threadId: number) {
    return apiGet<Thread>(`/api/boards/${boardAbbreviation}/threads/${threadId}`);
}

export function getThreads(boardAbbreviation: string) {
    return apiGet<Thread[]>(`/api/boards/${boardAbbreviation}/threads`);
}