import {type FormEvent, useState } from "react";
import { login } from "../api/auth";

export function LoginPage() {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    async function handleSubmit(event: FormEvent) {
        event.preventDefault();

        const response = await login({ username, password});

        localStorage.setItem("authToken", response.token);
        localStorage.setItem("username", response.username);

        window.location.href = "/";
    }

    return (
        <main>
            <h1>Log in</h1>

            <form onSubmit={handleSubmit}>
                <label>
                    Username
                    <input value={username} onChange={(event) => setUsername(event.target.value)} />
                </label>

                <label>
                    Password
                    <input type="password" value={password} onChange={(event) => setPassword(event.target.value)} />
                </label>

                <button type="submit">Log in</button>
            </form>
        </main>
    )
}