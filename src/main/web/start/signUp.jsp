<html>
<head>
    <meta charset="UTF-8">
    <title>Sign up</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>

<body class="w3-light-grey">
<div class="w3-container w3-blue-grey w3-opacity w3-center">
    <h1 class="w3-blue">Sign Up</h1>
</div>

<div class="w3-container w3-center">
    <form action = "/signUp" method="post">
        <div class="w3-bar w3-padding-large w3-padding-24">
            <div class="form-group">
                <label for="name">Enter name:</label>
                <input id="name" name="name" type="text" class="w3-input w3-animate-input w3-border w3-round-large" placeholder="Name" style="width: 100%" required />
            </div>
            <div class="form-group">
                <label for="mail">Enter e-mail:</label>
                <input id="mail" name ="mail" type="email" class="w3-input w3-animate-input w3-border w3-round-large"  placeholder="E-mail" style="width: 100%"  required/>
            </div>
            <div class="form-group">
                <label for="login">Enter login:</label>
                <input id="login" name="login" type="text" class="w3-input w3-animate-input w3-border w3-round-large" placeholder="Login" style="width: 100%" required />
            </div>
            <div class="form-group">
                <label for="password">Enter password:</label>
                <input id="password" name ="password" type="password" class="w3-input w3-animate-input w3-border w3-round-large"  placeholder="Password" style="width: 100%"  required/>
            </div>
            <label></label>
            <button type="submit" name ="sign up" class="w3-btn w3-red w3-border w3-round-large"  style="width: 100%">sign up</button>
        </div>
    </form>
</div>
</body>
</html>