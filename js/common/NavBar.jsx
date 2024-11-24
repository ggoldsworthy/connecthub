import { useEffect, useState } from "react"
import { navigateTo } from "../api/utils.js"
import logo from "../assets/logo.png"

export default function NavBar(props) {
  const [username, setUsername] = useState("")
  const [handle, setHandle] = useState("") // = `@${username.toLowerCase().replace(" ", "_")}`

  useEffect(() => {
    // fetch for current user
  }, [])

  return (
    <div id="nav-bar-container" onClick={() => navigateTo("/")}>
      <div id="nav-bar-home-container" >
        <img src={logo} alt="home logo" />
        <div>Connect Hub</div>
      </div>

      <div id="nav-bar-user-container">
        <img src="" alt=" " />
        <div className="username">{"Need Use Case"}</div>
        <div className="handle">{"@need_use_case"}</div>
      </div>
    </div>
  )
}