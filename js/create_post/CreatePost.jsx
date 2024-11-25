import { useRef } from "react"
import { navigateTo } from "../api/utils.js"
import NavBar from "../common/NavBar.jsx"

const HOME_URL = "/"

export default function CreatePost() {
  return (
    <>
      <NavBar />
      <CreatePostBox />
    </>
  )
}

function CreatePostBox() {
  const topicRef = useRef(null)
  const titleRef = useRef(null)
  const contentRef = useRef(null)

  const createPost = () => {

  }

  return (
    <div id="create-post-container">
      <form className="form">
        <div id="email-input" className="input-group">
            {/* TODO to implement getting existing topics, we could store topics in the local storage, or have a root component with topics as its state */}
            <label>Topic: </label>
            <input name="topic" id="topic-input" placeholder="general" ref={topicRef} />
        </div>

        <div id="password-input" className="input-group">
            <label>Title: </label>
            <input name="title" id="post-title-input" ref={titleRef} />
        </div>

        <div id="password-input" className="input-group">
            <label>Content: </label>
            <input name="content" id="post-content-input" ref={contentRef} />
        </div>

        <div id="sumbit-post-container">
          <button onClick={() => navigateTo(HOME_URL)}>Cancel</button>
          <button onClick={() => createPost()}>Post</button>
        </div>
      </form>
    </div>
  )
}