export default function Topics(props) {
  return (
    <div id="topics-container">
      <div>BROWSE TOPICS</div>
      <TopicEntry topic="test" numPosts={5} />
    </div>
  )
}

function TopicEntry(props) {
  return (
    <div className="topic-entry">
      <div className="topic-name">{props.topic}</div>
      <div className="topic-num-posts">{props.numPosts}</div>
    </div>
  )
}