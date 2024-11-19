export default function Entry(props) {
  const formType = props.formType.toUpperCase();

  return (
    <div className="entry-form">
      <h2 className="form-type">{formType}</h2>
      <div className="form-container">{props.form}</div>
      {formType === "LOGIN" ? <p className="switch-form-txt">Haven't signed up yet?</p> : <p className="switch-form-txt">Already have an account?</p>}
      {formType === "LOGIN" ? <button className="switch-form-btn">Sign Up</button> : <button className="switch-form-btn">Log In</button>}
    </div>
  )
}