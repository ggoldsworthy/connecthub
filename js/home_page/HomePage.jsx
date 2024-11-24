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
    <>
      <Topics />
      <Contents />
      <RecentActivities />
    </>
  )
}

