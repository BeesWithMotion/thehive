import { BrowserRouter, Routes, Route } from 'react-router-dom';
import { BoardListPage } from "./pages/BoardListPage.tsx";
import { BoardPage } from "./pages/BoardPage.tsx";
import { ThreadPage } from "./pages/ThreadPage.tsx";
import { CreateThreadPage } from "./pages/CreateThreadPage.tsx";
import {LoginPage} from "./pages/LoginPage.tsx";
import {RegisterPage} from "./pages/RegisterPage.tsx";

function App() {
  return (
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<BoardListPage />} />
          <Route path="/boards/:boardAbbreviation" element={<BoardPage />} />
          <Route path="/boards/:boardAbbreviation/new" element={<CreateThreadPage />} />
          <Route path="/boards/:boardAbbreviation/threads/:threadId" element={<ThreadPage />} />
          <Route path="/login" element={<LoginPage />} />
          <Route path="/register" element={<RegisterPage />} />
        </Routes>
      </BrowserRouter>
  );
}

export default App;