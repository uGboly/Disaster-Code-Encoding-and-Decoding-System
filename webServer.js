const express = require('express')
const app = express();
const port = 8080;
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

app.post('/data', (req,res)=> {
  let data ={"returnCode":0,"returnMsg":"SUCCESS","result":{"totalSize":7,"pageNum":1,"pageSize":20,"data":[{"id":22,"disasterCode":"110101009015200011222359592020101005","location":"北京市市辖区东城区东直门街道办事处工人体育馆社区居委会","carrier":"文字","origin":"舆情感知","disasterDate":"2000-11-22T15:59:59.000+00:00","category":"震情信息","label":"烈度","desc":"TEST"},{"id":23,"disasterCode":"130204201225202202022022223000402003","location":"河北省唐山市古冶区王辇庄乡甘雨沟村委会","carrier":"文字","origin":"其他","disasterDate":"2022-02-02T12:22:22.000+00:00","category":"供水","label":"受灾程度","desc":"DESC"},{"id":24,"disasterCode":"310115114209200111221359591400304003","location":"上海市市辖区浦东新区唐镇一心村村委会","carrier":"文字","origin":"危险区预评估工作组","disasterDate":"2001-11-22T05:59:59.000+00:00","category":"框架房屋","label":"受灾程度","desc":"123456"},{"id":25,"disasterCode":"320509107207202105220204001010302001.docx","location":"江苏省苏州市吴江区七都镇吴越村委会","carrier":"文字","origin":"后方地震应急指挥部","disasterDate":"2021-05-21T18:04:00.000+00:00","category":"砖木房屋","label":"一般损坏面积","desc":"src/main/resources/files/320509107207202105220204001010302001.docx"},{"id":26,"disasterCode":"110101009015200011222359592020101005","location":"北京市市辖区东城区东直门街道办事处工人体育馆社区居委会","carrier":"文字","origin":"舆情感知","disasterDate":"2000-11-22T15:59:59.000+00:00","category":"震情信息","label":"烈度","desc":"TEST"},{"id":27,"disasterCode":"130204201225202202022022223000402003","location":"河北省唐山市古冶区王辇庄乡甘雨沟村委会","carrier":"文字","origin":"其他","disasterDate":"2022-02-02T12:22:22.000+00:00","category":"供水","label":"受灾程度","desc":"DESC"},{"id":28,"disasterCode":"310115114209200111221359591400304003","location":"上海市市辖区浦东新区唐镇一心村村委会","carrier":"文字","origin":"危险区预评估工作组","disasterDate":"2001-11-22T05:59:59.000+00:00","category":"框架房屋","label":"受灾程度","desc":"123456"}]}}
  res.status(200).send(JSON.stringify(data));
})

app.listen(port, () => {
  console.log(`app listening on port ${port}`);
})