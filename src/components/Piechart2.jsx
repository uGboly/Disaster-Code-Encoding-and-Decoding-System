import React, { useEffect, useState } from 'react';
import Grid from '@mui/material/Unstable_Grid2';
import { Chart as ChartJS, ArcElement, Tooltip, Legend, Title} from 'chart.js';
import { Pie } from 'react-chartjs-2';
import axios from 'axios';

ChartJS.register(ArcElement, Tooltip, Legend,Title);

function ramdomColor() {
    let helper = () => Math.floor(Math.random() * 255);
    return `rgba(${helper()}, ${helper()}, ${helper()}, 0.2)`;
}

function Piechart2(props) {
    const [data, setData] = useState();

    useEffect(() => {
        if (!data) {
            axios.post('http://localhost:8080/data', {
                pageNum:1,
                pageSize:20
            })
            .then(
                res=>{
                    let map = new Array(5).fill(0);
                    res.data.result.data.forEach(e => {
                        switch (e.carrier) {
                            case "文字" :
                                map[0]++;
                                break;
                            case "图像" :
                                map[1]++;
                                break;
                            case "音频" :
                                map[2]++;
                                break;
                            case "视频" :
                                map[3]++;
                                break;
                            case "其他" :
                                map[4]++;
                                break;
                        }
                    });

                    console.log(map);

                    setData ( {
                        labels: ["文字","图像","音频","视频","其他"],
                        datasets: [
                        {
                            label: '灾情数量',
                            data: map,
                            backgroundColor: [
                                ramdomColor(),
                                ramdomColor(),
                                ramdomColor(),
                                ramdomColor(),
                                ramdomColor(),
                            ],
                            borderColor: [
                                ramdomColor(),
                                ramdomColor(),
                                ramdomColor(),
                                ramdomColor(),
                                ramdomColor(),
                            ],
                            borderWidth: 1,
                        },
                        ],
                    });
            })
            .catch(console.log);

            
        }});


    return(
        <Grid item xs={4}>
            {data ? <Pie options={{plugins: {title:{display:true,text:'信息种类统计'}}}} data={data} /> : ""}
        </Grid>
    );
}

export default Piechart2;