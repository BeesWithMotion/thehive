import {Link} from "react-router-dom";
import { logout } from "../api/auth";

export function LogoutButton() {
    const isLoggedIn = Boolean(localStorage.getItem("authToken"));

    function handleLogout() {
        logout();
        window.location.reload();
    }

    return (
        <div>
            {isLoggedIn ? (
                <button type="button" onClick={handleLogout}>
                    Log out
                </button>
            ) : (
                <p><Link to="/login">Log in</Link></p>
            )}
        </div>
    );
}