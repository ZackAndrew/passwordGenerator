function generatePassword() {
  const length = document.getElementById('length').value;
  const charset = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()_+";
  let password = "";
  for (let i = 0; i < length; i++) {
    const randomIndex = Math.floor(Math.random() * charset.length);
    password += charset[randomIndex];
  }
  document.getElementById('password').value = password;
}

function copyPassword() {
  const password = document.getElementById('password').value;
  
  if (navigator.clipboard) {
    navigator.clipboard.writeText(password)
      .then(() => {
        alert("Hasło skopiowane do schowka!");
      })
      .catch(err => {
        alert("Nie udało się skopiować hasła.");
        console.error(err);
      });
  } else {
    // Fallback na starsze przeglądarki
    const passwordField = document.getElementById('password');
    passwordField.select();
    document.execCommand('copy');
    alert("Hasło skopiowane do schowka (fallback).");
  }
}

