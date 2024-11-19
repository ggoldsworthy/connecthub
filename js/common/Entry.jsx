export default function Entry(props) {
    return (
        <div class="entry-form">
            <div class="form-type">{props.formType.toUpperCase()}</div>
            <div class="form-container">{props.form}</div>
            <div class="switch-form">
                {props.formType.toUpperCase == "LOGIN" ? (
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