import React from 'react';
import Grid from '@mui/material/Unstable_Grid2';
import Piechart from './Piechart';
import Linechart from './Linechart';

function Visualizer(props) {
    return(
        <Grid container>
            <Piechart/>
            <Linechart/>
        </Grid>
    );
}

export default Visualizer;