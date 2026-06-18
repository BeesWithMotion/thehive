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
          <Route path="/threads/:threadId" element={<ThreadPage />} />
          <Route path="/boards/:boardAbbreviation/new" element={<CreateThreadPage />} />
        </Routes>
      </BrowserRouter>
  );
}

export default App;