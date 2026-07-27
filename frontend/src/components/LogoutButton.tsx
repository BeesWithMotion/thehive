import {Link, useNavigate} from "react-router-dom";
import { logout } from "../api/auth";

export function LogoutButton() {
    const navigate = useNavigate();
    const isLoggedIn = Boolean(localStorage.getItem("authToken"));

    function handleLogout() {
        logout();
        navigate("/");
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