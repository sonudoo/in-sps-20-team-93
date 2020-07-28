import React from 'react';
import { render } from '@testing-library/react';
import App from './App';

test('renders hey there text', () => {
  const { getByText } = render(<App />);
  const linkElement = getByText(/Hey There!/i);
  expect(linkElement).toBeInTheDocument();
});
