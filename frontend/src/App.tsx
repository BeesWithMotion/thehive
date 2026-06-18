import { BrowserRouter, Routes, Route } from 'react-router-dom';
import { BoardListPage } from "./pages/BoardListPage.tsx";
import { BoardPage } from "./pages/BoardPage.tsx";
import { ThreadPage } from "./pages/ThreadPage.tsx";
import { CreateThreadPage } from "./pages/CreateThreadPage.tsx";

function App() {
  return (
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<BoardListPage />} />
          <Route path="/boards/:boardAbbreviation" element={<BoardPage />} />
          <Route path="/boards/:boardAbbreviation/new" element={<CreateThreadPage />} />
          <Route path="/boards/:boardAbbreviation/threads/:threadId" element={<ThreadPage />} />
        </Routes>
      </BrowserRouter>
  );
}

export default App;