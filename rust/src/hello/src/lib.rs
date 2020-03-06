pub struct ThreadPool;
//use std::error;
//use std::fmt;

enum PoolCreationError {
    ThreadCreation,
}

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
    pub fn new(size: usize) -> Result<ThreadPool, PoolCreationError> {

q
qq
:q
:q

    }

    pub fn execute<F>(&self, f: F)
        where
          F: FnOnce() + Send + 'static
          {

          }
}

