import {type FormEvent, useState } from "react";
import { register } from "../api/auth";

export function RegisterPage() {
    const [email, setEmail] = useState("");
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    async function handleSubmit(event: FormEvent) {
        event.preventDefault();

        const response = await register({ email, username, password});

        localStorage.setItem("authToken", response.token);
        localStorage.setItem("username", response.username);

        window.location.href = "/";
    }

    return (
        <main>
            <h1>Register a new account</h1>

            <form onSubmit={handleSubmit} style={{display: "flex", flexDirection: "column", justifyContent: "center", alignItems: "center", gap: "0.5rem"}}>
                <label>
                    Email
                    <input value={email} onChange={(event) => setEmail(event.target.value)} />
                </label>

                <label>
                    Username
                    <input value={username} onChange={(event) => setUsername(event.target.value)} />
                </label>

                <label>
                    Password
                    <input type="password" value={password} onChange={(event) => setPassword(event.target.value)} />
                </label>

                <button type="submit" style={{maxWidth: "0.5rem"}}>Register</button>
            </form>
        </main>
    )
}