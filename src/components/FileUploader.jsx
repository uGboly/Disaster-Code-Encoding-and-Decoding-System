import React, { useState } from 'react';
import  './FileUploader.css';
import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import Input from '@mui/material/Input';
import InputLabel from '@mui/material/InputLabel';
import Button from '@mui/material/Button';
import axios from 'axios';

function FileUploader(props) {
    const [infoFile, setInfoFile] = useState();
    const [fileContent, setFileContent] = useState();
    const [fileType, setFileType] = useState(false);

    function handleChange(e) {
        let file = e.target.files[0]
        setInfoFile(() => {
            console.log(file);

            setFileType(file.name.slice(file.name.lastIndexOf('.') + 1) === "csv");

            if (fileType) {
                let reader = new FileReader();
                reader.readAsText(infoFile);
                
                reader.onload = function() {
                    setFileContent(reader.result);
                };
            } else {
                setFileContent(URL.createObjectURL(file));
            }

            return file;
        });
        
        
    }

    function handleClick(e) {
        axios.postForm('http://localhost:8080/info', {
            'file': infoFile
        })
        .then(res => console.log("success"))
        .catch(console.log);
    }

    return (
        <div>
            <Box component="form" >
                <InputLabel htmlFor="file-selector">选择震情描述文件(csv或多媒体文件)</InputLabel>
                <Input id="file-selector" inputProps={{type:"file"}} onChange= {handleChange}/>
            </Box>
            
            
            <Box sx={{m:6, width:"100%",height:"70%"}}>
            {infoFile ? 
                    fileType ?
                        <Typography className="file-content" sx={{width:"90%",height:"100%"}}>
                            你选择的文件如下
                            <br/>
                            {fileContent}
                        </Typography> :
                        <object data={fileContent} width="90%" height="100%"> </object>
                :""
            }
            </Box>

            <Button sx={{mb:6}} variant="contained" onClick={handleClick} >上传文件</Button>
        </div>


    );
}

export default FileUploader;