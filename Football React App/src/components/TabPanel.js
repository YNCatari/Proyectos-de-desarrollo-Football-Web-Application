import React from 'react';
import Box from '@material-ui/core/Box';
import PropTypes from 'prop-types';

function TabPanel(props)
{
    const { children, value, index, ...other } = props;
    return (
        <Box
          component="div"
          role="tabpanel"
          hidden={value !== index}
          id={`scrollable-auto-tabpanel-${index}`}
          {...other}
        >
          <Box>{value === index && children}</Box>
        </Box>
    );
}

TabPanel.propTypes = {
    children: PropTypes.node,
    index: PropTypes.any.isRequired,
    value: PropTypes.any.isRequired,
};

export default TabPanel;