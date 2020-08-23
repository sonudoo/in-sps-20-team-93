import React from 'react';
import { render, getByRole } from '@testing-library/react';
import GetPathComponent from './GetPathComponent';

test('renders <GetPathComponent /> with Path Information Success Message', () => {
  const { getByTitle } = render(<GetPathComponent />);
  
  const linkElement = getByTitle('PathInfoSuccess');
  
  expect(linkElement).toBeInTheDocument();
});

test('renders <GetPathComponent /> with Path Information Failure Message', () => {
  const { getByTitle } = render(<GetPathComponent />);
    
  const linkElement = getByTitle('PathInfoFailure');
    
  expect(linkElement).toBeInTheDocument();
});

test('renders <GetPathComponent /> with Previous Path Button', () => {
  const { getByRole } = render(<GetPathComponent />);
  
  const linkElement = getByRole('DisplayPreviousPath');
  
  expect(linkElement).toBeInTheDocument();
});

test('renders <GetPathComponent /> with Next Path Button', () => {
  const { getByRole } = render(<GetPathComponent />);
  
  const linkElement = getByRole('DisplayNextPath');
  
  expect(linkElement).toBeInTheDocument();
});