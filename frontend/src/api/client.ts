export async function apiGet<T>(path: string): Promise<T> {
    const response = await fetch(path);

    if (!response.ok) {
        throw new Error(`Request failed: ${response.status}`);
    }

    return response.json() as Promise<T>;
}

export async function apiPost<TResponse, TBody>(path: string, body: TBody): Promise<TResponse> {
    const response = await fetch(path, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(body),
    });

    if (!response.ok) {
        throw new Error(`Request failed: ${response.status}`);
    }

    return response.json() as Promise<TResponse>;
}