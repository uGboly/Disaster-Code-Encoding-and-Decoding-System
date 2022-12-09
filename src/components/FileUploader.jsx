import React, { useState } from 'react';
import  './FileUploader.css';
import Box from '@mui/material/Box';
import Input from '@mui/material/Input';
import InputLabel from '@mui/material/InputLabel';
import Button from '@mui/material/Button';
import axios from 'axios';

function FileUploader(props) {
    const [infoFile, setInfoFile] = useState();
    const [fileContent, setFileContent] = useState();

    function handleChange(e) {
        let file = e.target.files[0]
        setInfoFile(() => {
            console.log(file);

            setFileContent(URL.createObjectURL(file));

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
                <InputLabel htmlFor="file-selector">选择震情描述文件(Excel或多媒体文件)</InputLabel>
                <Input id="file-selector" inputProps={{type:"file"}} onChange= {handleChange}/>
            </Box>
            
            
            <Box sx={{m:6, width:"100%",height:"70%"}}>
            {infoFile ? <object data={fileContent} width="90%" height="100%"> </object> :""}
            </Box>

            <Button sx={{mb:6}} variant="contained" onClick={handleClick} >上传文件</Button>
        </div>


    );
}

export default FileUploader;