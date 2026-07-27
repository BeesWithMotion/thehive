import { Link } from "react-router-dom";
import {LogoutButton} from "./LogoutButton.tsx";

export function SideCard() {
    const username = localStorage.getItem("username");

    return (
        <div>
            <>
                <aside
                    style={{
                        position: "fixed",
                        right: "calc(50% + 590px)",
                        width: "180px",
                        border: "1px solid var(--border)",
                        borderRadius: "8px",
                        padding: "1rem",
                    }}
                >
                    <h2>Account</h2>
                    <p>
                        {!username ? (
                            <p>
                                <Link to="/register">Register</Link> * <Link to="/login">Login</Link>
                            </p>
                        ) : (
                            <div>
                                <p>{username}</p>
                                <LogoutButton />
                            </div>
                        )}
                    </p>
                </aside>
            </>
        </div>
    )
}