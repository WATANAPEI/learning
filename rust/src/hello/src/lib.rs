use std::thread;
pub struct ThreadPool {
    threads: Vec<thread::JoinHandle<()>>,
}
//use std::error;
//use std::fmt;

/* UNdone
impl fmt::Display for PoolCreationError {
    fn fmt(&self, f: &mut fmt::Formatter) -> fmt::Result {
        match *self {
            PoolCreationError::ThreadCreation
            */

impl ThreadPool {
    /// 新しいThreadPoolを生成する。
    ///
    /// sizeがプールのスレッド数です。
    /// # panic
    ///
    /// sizeが0なら、`new`関数はパニックします。
    ///
    /// Create a new ThreadPool.
    pub fn new(size: usize) -> ThreadPool {
        assert!(size > 0 );

        let mut threads = Vec::with_capacity(size);

        for _ in 0..size {
            // create new threads and store them in the vector
        }

        ThreadPool {
            threads
        }
    }

    pub fn execute<F>(&self, f: F)
        where
          F: FnOnce() + Send + 'static
          {

          }
}

