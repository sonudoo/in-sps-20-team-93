import React from 'react';
import { render } from '@testing-library/react';
import { SubmitJobForm }from './SubmitJobForm';

test('renders <input /> with placeholder Name', () => {
  const { getByPlaceholderText } = render(<SubmitJobForm />);
  const linkElement = getByPlaceholderText('Name');
  expect(linkElement).toBeInTheDocument();
});

test('renders <input /> with placeholder Phone', () => {
  const { getByPlaceholderText } = render(<SubmitJobForm />);
  const linkElement = getByPlaceholderText('Phone');
  expect(linkElement).toBeInTheDocument();
});

test('renders <input /> with placeholder Latitudes', () => {
  const { getByPlaceholderText } = render(<SubmitJobForm />);
  const linkElement = getByPlaceholderText('Latitudes');
  expect(linkElement).toBeInTheDocument();
});

test('renders <input /> with placeholder Longitudes', () => {
  const { getByPlaceholderText } = render(<SubmitJobForm />);
  const linkElement = getByPlaceholderText('Longitudes');
  expect(linkElement).toBeInTheDocument();
});

