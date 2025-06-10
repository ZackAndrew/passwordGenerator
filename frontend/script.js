document.getElementById('generateBtn').addEventListener('click', generatePassword);

function generatePassword() {
    const length = document.getElementById('length').value;
    const types = [];
    if (document.getElementById('upper').checked) types.push('UPPER');
    if (document.getElementById('lower').checked) types.push('LOWER');
    if (document.getElementById('digits').checked) types.push('DIGITS');
    if (document.getElementById('symbols').checked) types.push('SYMBOLS');

    if (types.length === 0) {
        alert('Wybierz przynajmniej jeden typ znaków!');
        return;
    }

    const url = `http://localhost:8080/api/password/generate?length=${length}` + types.map(t => `&types=${t}`).join('');

    const username = 'admin';
    const password = 'admin';
    const basicAuth = 'Basic ' + btoa(username + ':' + password);

    fetch(url, {
        headers: {
            'Authorization': basicAuth
        }
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Błąd sieci lub serwer nie odpowiada');
        }
        return response.text();
    })
    .then(password => {
        document.getElementById('result').textContent = password;
    })
    .catch(error => {
        document.getElementById('result').textContent = 'Błąd: ' + error.message;
    });
}
