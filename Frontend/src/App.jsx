//import './App.css'
import { useState } from 'react'
import { Route, Routes } from 'react-router-dom'
import Layout from './components/Layout'
import ForsideHoved from './pages/ForsideHoved'
import Show404 from './components/show404'
import Courses from './pages/Courses'
function App() {
  return (
    <Routes>
      <Route path="/" element={<Layout />} >
        <Route index element={<ForsideHoved />} />
        <Route path="/Courses" element={<Courses />} />
        <Route path="*" element={<Show404 />} />
      </Route>
    </Routes>


  )
}

export default App
