import NavBar from "../common/NavBar.jsx"
import Topics from "./Topics.jsx"
import Contents from "./Contents.jsx"
import RecentActivities from "./RecentActivities.jsx"

export default function HomePage() {
  return (
    <>
      <NavBar />
      <HomeContent />
    </>
  )
}

function HomeContent() {
  return (
    <div id="home-page-container">
      <Topics />
      <Contents />
      <RecentActivities />
    </div>
  )
}

