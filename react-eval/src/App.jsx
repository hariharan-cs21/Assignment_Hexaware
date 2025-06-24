import { BrowserRouter, Routes, Route } from "react-router"
import UserList from "./Components/UserList"

function App() {
  const token = "f430490e1d27a76a5ff5dbe715ac0d942a233c3617c3cb2de8f1598e36c1044b"
  localStorage.setItem("token", token)
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<UserList />} />
      </Routes>
    </BrowserRouter>

  )
}

export default App
