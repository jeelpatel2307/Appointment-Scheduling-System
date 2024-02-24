# app.py

from flask import Flask, render_template, request, redirect, url_for

app = Flask(__name__)

# Store appointments in a dictionary
appointments = {}

@app.route('/')
def index():
    return render_template('index.html', appointments=appointments)

@app.route('/schedule', methods=['GET', 'POST'])
def schedule():
    if request.method == 'POST':
        date = request.form['date']
        time = request.form['time']
        purpose = request.form['purpose']
        appointment_id = len(appointments) + 1
        appointments[appointment_id] = {'date': date, 'time': time, 'purpose': purpose}
        return redirect(url_for('index'))
    return render_template('schedule.html')

if __name__ == '__main__':
    app.run(debug=True)







<!-- templates/index.html -->

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Appointment Scheduling System</title>
</head>
<body>
    <h1>Appointment Scheduling System</h1>
    <a href="/schedule">Schedule Appointment</a>
    <h2>Appointments</h2>
    <ul>
        {% for appointment_id, appointment in appointments.items() %}
            <li>
                <strong>Date:</strong> {{ appointment.date }}<br>
                <strong>Time:</strong> {{ appointment.time }}<br>
                <strong>Purpose:</strong> {{ appointment.purpose }}
            </li>
        {% endfor %}
    </ul>
</body>
</html>







<!-- templates/schedule.html -->

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Schedule Appointment</title>
</head>
<body>
    <h1>Schedule Appointment</h1>
    <form action="/schedule" method="post">
        <label for="date">Date:</label>
        <input type="date" id="date" name="date" required><br>
        <label for="time">Time:</label>
        <input type="time" id="time" name="time" required><br>
        <label for="purpose">Purpose:</label>
        <input type="text" id="purpose" name="purpose" required><br>
        <button type="submit">Schedule</button>
    </form>
</body>
</html>
