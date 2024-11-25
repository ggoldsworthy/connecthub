export default function Contents(props) {
  return (
    <div id="contents-container">
      <div className="content-header">
        <div>See the latest posts</div>
        <button>+ Create Post</button>
      </div>

      <PostBox authorPfp="" author="Doodle Wacker" timeStamp="Nov. 24, 2024" title="Test title very long" postContent="i am fucking cooked" topic="test" />
    </div>
  )
}

function PostBox(props) {
  return (
    <div className="post-box-container">
      <div className="post-box-info">
        <img className="post-box-author-pfp" src={props.authorPfp} alt=" " />
        <div className="post-box-author">{props.author}</div>
        <div className="post-box-timestamp">{props.timeStamp}</div>
      </div>

      <div className="post-box-title">{props.title}</div>
      <div className="post-box-content">{props.postContent}</div>
      <div className="post-box-topic">{props.topic}</div>
    </div>
  )
}