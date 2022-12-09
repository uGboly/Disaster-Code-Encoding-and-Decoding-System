import React from 'react';
import Grid from '@mui/material/Unstable_Grid2';
import Piechart from './Piechart';
import Piechart2 from './Piechart2';
import Linechart from './Linechart';

function Visualizer(props) {
    return(
        <Grid container>
            <Piechart/>
            <Linechart/>
            <Piechart2/>
        </Grid>
    );
}

export default Visualizer;