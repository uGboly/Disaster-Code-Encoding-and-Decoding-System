const express = require('express')
const app = express();
const port = 3000;
var cors = require("cors");
const bodyParser = require('body-parser');
const multer = require('multer'); // v1.0.5
const upload = multer(); // for parsing multipart/form-data

app.use(cors());
app.use(bodyParser.json()) // for parsing application/json
app.use(bodyParser.urlencoded({ extended: true })) // for parsing application/x-www-form-urlencoded
app.use(express.static(__dirname));

app.post('/info', upload.single('file'), (req, res) => {
  console.log("收到文件"+req.file.originalname);
})

app.listen(port, () => {
  console.log(`app listening on port ${port}`);
})