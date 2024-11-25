import NavBar from "../common/NavBar.jsx"
import Topics from "./Topics.jsx"
import Contents from "./Contents.jsx"
import RecentActivities from "./RecentActivities.jsx"
import { useEffect, useState } from "react"
import api from './axios.config.js'

export default function HomePage() {
  return (
    <>
      <NavBar />
      <HomeContent />
    </>
  )
}

function HomeContent() {
  const [posts, setPosts] = useState([])

  useEffect(() => {
    api
    .get("/posts")
    .then(response => {
      console.log("HI")
    })
    .catch(() => {
      window.alert("Error fetching posts")
    })
  }, [])

  return (
    <div id="home-page-container">
      <Topics />
      <Contents />
      <RecentActivities />
    </div>
  )
}

