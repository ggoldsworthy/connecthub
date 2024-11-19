import { useRef } from "react"
import Entry from "../common/Entry.jsx"

export default function SignUp() {
  return <Entry formType="signup" form={<SignUpForm />} />
}

function SignUpForm() {
  const emailRef = useRef(null)
  const usernameRef = useRef(null)
  const fullNameRef = useRef(null)
  const passwordRef = useRef(null)
  const confirmPasswordRef = useRef(null)
  const matchMessage = useRef(null)

  const handlePasswordChange = () => {
    const passwordVal = passwordRef.current.value;
    const confirmPasswordVal = confirmPasswordRef.current.value;
    matchMessage.current.innerText = passwordVal === confirmPasswordVal ?  "" : "password don't match";
  }
  
  return (
    <form className="form" action="">
      <div id="email-input" className="input-group">
        <label>Email: </label>
        <input name="email" id="email-input" placeholder="doctor.giggle.touch@gmail.com" ref={emailRef} />
      </div>

      <div id="username-input" className="input-group">
        <label>Username: </label>
        <input name="username" id="username-input" placeholder="ProfessorHankySpanky" ref={usernameRef} />
      </div>
      
      <div id="full-name-input" className="input-group">
        <label>Full Name: </label>
        <input name="fullName" id="full-name-input" placeholder="Doodle Wacker" ref={fullNameRef} />
      </div>

      <div id="password-input" className="input-group">
        <label>Password: </label>
        <input name="password" id="password-input" placeholder="" ref={passwordRef} onChange={handlePasswordChange} />
      </div>

      <div id="confirm-password-input" className="input-group">
        <label>Confirm Password: 
          <span className="password-match" ref={matchMessage}></span>
        </label>
        <input name="confirmPassword" id="confirm-password-input" placeholder="" ref={confirmPasswordRef} onChange={handlePasswordChange} />
      </div>

      <button type="submit">SIGNUP</button>
    </form>
  )
}