const express = require('express')
const app = express()

/**
 * Run Server
 */
app.listen(3000)

// JSON
app.get('/json', (req, res) => {
    res.status(200)
        .json(
            {
                'greetings': 'Hi There',
            }
        )
})

// Send String
app.get('/status', (req, res) => {
    res.status(400)
        .send('Bad Request')
})

// Send File
app.get('/file', (req, res) => {
    res.status(200)
        .download("server.js")
})

// Render HTML
// Pass Data as Parameter to 'render' method
app.set('view engine', 'pug')
app.get('/html', (req, res) => {
    res.render("index", {'title': 'ExpressServer'})
})

const userRouter = require('./routes/users')
app.use('/users', userRouter)

app.use(express.static('public'))
