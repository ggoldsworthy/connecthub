export default function Entry(props) {
    return (
        <div className="entry-form">
            <h2 className="form-type">{props.formType.toUpperCase()}</h2>
            <div className="form-container">{props.form}</div>
            <div className="switch-form">
                {props.formType.toUpperCase === "LOGIN" ? (
                    <>
                        <p>Haven't signed up yet?</p>
                        <button>Sign Up</button>
                    </>
                ) : (
                    <>
                        <p>Already have an account?</p>
                        <button>Log In</button>
                    </>
                )}
            </div>
        </div>
    )
}