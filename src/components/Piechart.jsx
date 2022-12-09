import React, { useEffect, useState } from 'react';
import Grid from '@mui/material/Unstable_Grid2';
import { Chart as ChartJS, ArcElement, Tooltip, Legend, Title} from 'chart.js';
import { Pie } from 'react-chartjs-2';
import axios from 'axios';

ChartJS.register(ArcElement, Tooltip, Legend,Title);

function Piechart(props) {
    const [data, setData] = useState();

    useEffect(() => {
        if (!data) {
            axios.post('http://localhost:8080/data', {
                pageNum:1,
                pageSize:20
            })
            .then(
                res=>{
                    let map = new Map();
                    res.data.result.data.forEach(e => {
                        if (map.has(e.category)) {
                            map.set(e.category, map.get(e.category) + 1);
                        } else {
                            map.set(e.category, 1);
                        }
                    });

                    map = Array.from(map.entries()).filter((e, index)=> index <= 3);
                    console.log(map);

                    setData ( {
                        labels: map.map(e => e[0]),
                        datasets: [
                        {
                            label: '灾情数量',
                            data: map.map(e => e[1]),
                            backgroundColor: [
                            'rgba(255, 99, 132, 0.2)',
                            'rgba(54, 82, 235, 0.2)',
                            'rgba(255, 206, 86, 0.2)',
                            'rgba(75, 192, 192, 0.2)',
                            ],
                            borderColor: [
                            'rgba(255, 99, 132, 1)',
                            'rgba(54, 162, 235, 1)',
                            'rgba(255, 206, 86, 1)',
                            'rgba(75, 192, 192, 1)',
                            ],
                            borderWidth: 1,
                        },
                        ],
                    });
            })
            .catch(console.log);

            
        }});


    return(
        <Grid item xs={6}>
            {data ? <Pie options={{plugins: {title:{display:true,text:'信息种类统计'}}}} data={data} /> : ""}
        </Grid>
    );
}

export default Piechart;