import React, { useState } from 'react';
import Grid from '@mui/material/Unstable_Grid2';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import MenuItem from '@mui/material/MenuItem';
import { DataGrid } from '@mui/x-data-grid';
import Divider from '@mui/material/Divider';
import Button from '@mui/material/Button';
import axios from 'axios';

function DataBrowser(props) {
    const [query, setQuery] = useState({
        pageNum: 1, // 当前页数，必填，默认1
        pageSize: 20 // 每页数量，选填，默认20
    });

    const [data, setData] = useState();
    const handleChange = (e) => {
        setQuery({
            ...query,
            [e.target.name] : e.target.value
        })
    }

    function handleClick(e) {
        const formData = new FormData();
        for (let [key, value] of Object.entries(query)) {
            formData.append(key, value);
        }

        axios.post('http://localhost:8080/data', formData)
        .then(res => setData(res.data))
        .catch(console.log);
    }


    const columns = [
        { field: 'id', headerName: 'ID', width: 80 },
        {
            field: 'disasterCode',
            headerName: '灾情码',
            width: 90,
        },
        {
            field: 'location',
            headerName: '灾情地区',
            width: 90,
        },
        {
            field: 'carrier',
            headerName: '载体形式',
            width: 90,
        },
        {
            field: 'origin',
            headerName: '来源形式',
            width: 90,
        },
        {
            field: 'disasterDate',
            headerName: '灾情时间',
            width: 90,
        },
        {
            field: 'category',
            headerName: '信息种类',
            width: 90,
        },
        {
            field: 'label',
            headerName: '灾情指标',
            width: 90,
        },
    ];

    // const rows =[
    //     {
    //         "id":"1",
    //         "disasterCode":"1",
    //         "location":"1",
    //         "carrier":"1",
    //         "origin":"1",
    //         "disasterDate":"1",
    //         "category":"1",
    //         "label":"1"
    //     },
    //     {
    //         "id":"1",
    //         "disasterCode":"1",
    //         "location":"1",
    //         "carrier":"1",
    //         "origin":"1",
    //         "disasterDate":"1",
    //         "category":"1",
    //         "label":"1"
    //     },
    //     {
    //         "id":"1",
    //         "disasterCode":"1",
    //         "location":"1",
    //         "carrier":"1",
    //         "origin":"1",
    //         "disasterDate":"1",
    //         "category":"1",
    //         "label":"1"
    //     }
    // ];


    return (
        <Grid container spacing={3}>
            <Box component="form" sx={{'& .MuiTextField-root': { m: 2 }}} >
                <TextField name="id" id="id" label="灾情信息ID" value={query.id} onChange={handleChange} variant="outlined" />
                <TextField name="disasterCode" id="disasterCode" label="灾情码" value={query.disasterCode} onChange={handleChange} variant="outlined" />
                <TextField name="location" id="location" label="灾情地区" value={query.location} onChange={handleChange} variant="outlined" />
                <TextField name="carrier" id="carrier" select label="载体形式" value={query.carrier} onChange={handleChange} variant="outlined" helperText="请选择载体形式">
                    <MenuItem key="1" value ="文字">文字</MenuItem>
                    <MenuItem key="2" value ="图像">图像</MenuItem>
                    <MenuItem key="3" value ="音频">音频</MenuItem>
                    <MenuItem key="4" value ="视频">视频</MenuItem>
                    <MenuItem key="5" value ="其他">其他</MenuItem>
                </TextField>
                <TextField name="origin" id="origin" label="来源形式" value={query.origin} onChange={handleChange} variant="outlined" />
                <TextField name="disasterDate" id="disasterDate" value={query.disasterDate} inputProps={{type:"date"}} onChange={handleChange} helperText="请选择灾情时间" />
                <TextField name="category" id="category" label="信息种类" value={query.category} onChange={handleChange} variant="outlined" />
                <TextField name="label" id="label" label="灾情指标" value={query.label} onChange={handleChange} variant="outlined" />
            </Box>
            <Grid item xs={12}>
                <Button sx={{mb:6}} variant="contained" onClick={handleClick} >查询数据</Button>
            </Grid>
            <Divider/>
            <Grid item xs={12}>
                <Box sx={{height:500,width:'100%'}}>
                    <DataGrid
                        rows={data.result}
                        columns={columns}
                        pageSize={query.pageSize}
                        disableSelectionOnClick
                        experimentalFeatures={{ newEditingApi: true }}
                    />
                </Box>
            </Grid>
        </Grid>
    );
}

export default DataBrowser;