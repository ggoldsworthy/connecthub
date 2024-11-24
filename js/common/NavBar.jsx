import { useState } from "react"
import logo from "../assets/logo.png"

export default function NavBar(props) {
  const [username, setUsername] = useState("")

  return (
    <div id="nav-bar-container">
      <div id="nav-bar-home-container" >
        <img src={logo} alt="home logo" />
        <div>Connect Hub</div>
      </div>

      <div id="nav-bar-user-container">
        <div>{username}</div>
        <img src="" alt="user profile pic" />
      </div>
    </div>
  )
}