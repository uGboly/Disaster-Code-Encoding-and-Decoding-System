import React, { useEffect, useState } from 'react';
import Grid from '@mui/material/Unstable_Grid2';
import {
    Chart as ChartJS,
    CategoryScale,
    LinearScale,
    PointElement,
    LineElement,
    Title,
    Tooltip,
    Legend,
} from 'chart.js';
import { Line } from 'react-chartjs-2';
import axios from 'axios';

ChartJS.register(
    CategoryScale,
    LinearScale,
    PointElement,
    LineElement,
    Title,
    Tooltip,
    Legend
  );
  
export const options = {
    responsive: true,
    plugins: {
        title: {
            display: true,
            text: '历年灾情数量统计',
        },
    },
};
  
function Linechart(props) {
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
                        let year = e.disasterDate.slice(0,4);
                        if (map.has(year)) {
                            map.set(year, map.get(year) + 1);
                        } else {
                            map.set(year, 1);
                        }
                    });

                    map = Array.from(map.entries());
                    map.sort((a, b) => a[0] - b[0]);
                    console.log(map);
                    let labels = map.map(e => e[0]);

                    setData ( {
                        labels,
                        datasets: [
                          {
                            label: '灾情数量',
                            data: map.map(e => e[1]),
                            borderColor: 'rgb(255, 99, 132)',
                            backgroundColor: 'rgba(255, 99, 132, 0.5)',
                          }
                        ],
                      });
            })
            .catch(console.log);

            
        }});


    return(
        <Grid item xs={6}>
            {data ? <Line options={options} data={data} /> : ""}
        </Grid>
    );
}

export default Linechart;